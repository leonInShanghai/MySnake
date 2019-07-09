package cn.bobo.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import cn.bobo.snake.listener.SnakeListener;
import cn.bobo.snake.util.Global;

/**
 * Created by Leon on 2019/1/13. Copyright © Leon. All rights reserved.
 * Functions: 本次游戏贪吃蛇中的蛇类
 */
public class Snake {
	
	//上
	public static final int UP = -1;
	
	//下
	public static final int DOWN = 1;
	
	//左
	public static final int LEFT = 2;
	
	//右
	public static final int RIGHT = -2;
	
	//方向变量
	private int oldDirection,newDirection;
	
	//蛇身体最后一段的 坐标点 X,Y
	private Point oldTail;
	
	//蛇的坐标集合
    private LinkedList<Point> body = new LinkedList<Point>();
	
	//点击事件的集合
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	
	//蛇的生命变量 为false的时候 将不在动
	private boolean life;

	
	public Snake(){
		//初始化蛇的位置
		init();
	}
	
	
	private void init() {
		
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		
		for (int i = 0; i < 3; i++) {
			//body.add(new Point(x,y));
			body.addLast(new Point(x-i,y));
		}
		
		oldDirection = newDirection = RIGHT;
		life = true;//蛇的生命为true即活
	}
	
	//让蛇死掉 不再活动
	public void die() {
		life = false;
	}
	
	/**(让)蛇移动的方法*/
	public void move() {
		System.out.println("snake's move");
		
		//如果新的方向 + 原来的方向 等于0 说明是相反方向     != 0则不是相反方向
		if (oldDirection + newDirection != 0) {
			oldDirection = newDirection;
		}
		
		//采取 去尾 加头的方式让蛇前进 ①去尾巴
		oldTail = body.removeLast();
		
		//得到当前蛇头的坐标
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		
		switch (oldDirection) {
		
			case UP://蛇向上前进
				y--;
				
				//（没有墙 石头的情况下）蛇走出边界从对岸边界回来
				if (y < 0) {
					y = Global.HEIGHT - 1;
				}
				
				break;
	
			case DOWN://蛇向下前进
				y++;
				
				//（没有墙 石头的情况下）蛇走出边界从对岸边界回来
				if (y >= Global.HEIGHT) {
					y = 0;
				}
				
				break;
				
			case LEFT://蛇向左前进
				x--;
				
				//（没有墙 石头的情况下）蛇走出边界从对岸边界回来
				if (x < 0) {
					x = Global.WIDTH - 1;
				}
				
				break;	
				
			case RIGHT://蛇向右前进
				x++;
				
				//（没有墙 石头的情况下）蛇走出边界从对岸边界回来
				if (x >= Global.WIDTH) {
					x = 0;
				}
				
				break;		
			
		}
		
		Point newHead = new Point(x,y);
		
		
		//②加头
		body.addFirst(newHead);
	}
	
	
	/**(让)蛇改变方向的方法*/
	public void changeDirection(int direction) {	
		System.out.println("snake's changeDirection");
		
		this.newDirection = direction;
	
	}
	
	/**(让)蛇吃食物的方法*/
	public void eatFood() {
		System.out.println("snake's eatFood");
		
		//采取 不去尾 加头的方式让蛇吃到食物后长身体
		body.addLast(oldTail);
		
		//Leon增加调用这个方法解决蛇吃到食物停滞一下的问题
		move();
	}
	
	/**(判断)蛇吃到自己的方法*/
	public boolean isEatBody() {
		System.out.println("snake's isEatBody");
		
		//当蛇头的坐标和身体的任何一个坐标重合都算吃到自己 for循环必须从 1开始  因为0是蛇头 
		for (int i = 1; i < body.size(); i++) {
			if (body.get(i).equals(getHead())) {
				System.out.println("吃到自己");
				return true;
			}
		}
		
		return false;
	}
	
	/**(让)蛇显示的方法*/
	public void drawMe(Graphics g) {
		System.out.println("snake's drawMe");
		
		g.setColor(Color.BLUE);
		
		//遍历蛇位置的集合 画出蛇
		for (Point p : body) {
			g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
	}
	
	
	/**获取蛇头坐标的方法*/
	public Point getHead() {
		return body.getFirst();
	}
	
	/**开辟子线程使蛇不停移动的内部类*/
	private class SnakeDriver implements Runnable{

		/**
		 * 开辟子线程不停的让蛇移动前进
		 */
		@Override
		public void run() {
			
			//在while循环中调用蛇的move方法使之不停的前进
			while (life) {
				
				//调用移动的方法
				move();
				
				//循环遍历所有的监听器
				for (SnakeListener snakeListener : listeners) {
					snakeListener.snakeMoved(Snake.this);
				}
				
				try {
					//让线程睡眠起到 变速的作用
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					//Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**启动线程让蛇不停前进*/
	public void start() {
		new Thread(new SnakeDriver()).start();
	}
	
	
	//设置 点击事件的监听 注意：add 是往集合中添加 这里用 add比较合适  比set合适
	public void addSnakeListener(SnakeListener snakeListener) {
		//避免空指针
		if (snakeListener != null) {
			this.listeners.add(snakeListener);
		}
	}
}
