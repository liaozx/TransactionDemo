package com.lzx.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class Zktest {

	public static void main(String[] args) {

		try {
			ZooKeeper zk = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 3000, new Watcher() {

				@Override
				public void process(WatchedEvent event) {
					System.out.println(event);

				}

			});
			zk.create("/dubbo/providers/s1", "hello".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			Thread.sleep(10000);
			zk.close();
			System.out.println("over!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
