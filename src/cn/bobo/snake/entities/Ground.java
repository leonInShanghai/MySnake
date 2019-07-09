package cn.bobo.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import cn.bobo.snake.util.Global;

/**
 * Created by Leon on 2019/1/13. Copyright © Leon. All rights reserved.
 * Functions: 本次游戏贪吃蛇的地板(墙  石头)类
 */
public class Ground {

	//二维数组 存储所有的石头的位置 为1代表有石头为0代表没有石头
	private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];
	
	
	public Ground(){
		//产生石头
		for (int x = 0; x < rocks.length; x++) {
			//第一行全部是石头
			rocks[x][0] =1;
			
			//最后一行全部是石头
			rocks[x][rocks.length -1] =1;
			
		}
	}
	
	/**
	 * 判断蛇是否吃到石头（撞墙）
	 * @param snake
	 * @return 是 / 否
	 */
	public boolean isSnakeEatRock(Snake snake) {
		
		System.out.println("判断蛇是否吃到石头（撞墙）");
		
		//遍历二维数组 判断蛇是否吃到石头：蛇头的坐标和石头的坐标相同就返回true
		for (int x = 0; x < rocks.length; x++) {
			for (int y = 0; y < rocks[x].length; y++) {
				//判断数组中的元素是否为1 为1代表有石头为0代表没有石头
				if (rocks[x][y] == 1 && x == snake.getHead().x && y == snake.getHead().y) {
					System.out.println("吃到石头");
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**随机产生坐标的方法*/
	public Point getPoint() {
		//创建一个随机数产生器
		Random random = new Random();
		
		//do while (rocks[x][y] == 1) 就会一直循环下去直到 == 0 即不是石头才会停止
		int x = 0,y = 0;
		
		do {
			//产生一个0到最大列数之间的X坐标
			x = random.nextInt(Global.WIDTH);
			//产生一个0到最大行数之间的y坐标
			y = random.nextInt(Global.HEIGHT);
		} while (rocks[x][y] == 1);
		
		
		//返回随机坐标
		return new Point(x,y);
	}
	
	/**地板(墙  石头) 画自己的方法*/
	public void drawMe(Graphics g) {
		System.out.println("地板(墙  石头) 画自己的方法");
		
		//给石头设置颜色灰色
		g.setColor(Color.DARK_GRAY);
		
		//遍历二维数组 数组中为1的地方画出石头
		for (int x = 0; x < rocks.length; x++) {
			for (int y = 0; y < rocks[x].length; y++) {
				//判断数组中的元素是否为1 为1代表有石头为0代表没有石头
				if (rocks[x][y] == 1) {
					//开始画石头
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
}
