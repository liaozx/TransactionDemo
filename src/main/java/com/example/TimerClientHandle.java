package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimerClientHandle implements Runnable {

	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	private int port;

	public TimerClientHandle(int port) {
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void stop() {
		this.stop = true;
	}

	@Override
	public void run() {

		try {
			doConnect();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		}

		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectKeys.iterator();

				SelectionKey key = null;

				while (it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (selector != null) {
			try {
				selector.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			SocketChannel sc = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				} else {
					System.exit(1);
				}
			}
			if (key.isReadable()) {
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if (readBytes > 0) {
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);

					String body = new String(bytes, "UTF-8");
					System.out.println("client get" + body);
					stop();
				} else if (readBytes < 0) {
					key.cancel();
					sc.close();
				} else
					;
			}

		}

	}

	private void doConnect() throws IOException {

		if (socketChannel.connect(new InetSocketAddress("127.0.0.1", port))) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite(socketChannel);
		} else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	private void doWrite(SocketChannel channel) throws IOException {
		byte[] bytes = "query time order".getBytes();

		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		channel.write(writeBuffer);
		if (!writeBuffer.hasRemaining()) {
			System.out.println("send  succeed");
		}

	}

}
