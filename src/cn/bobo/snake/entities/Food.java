package cn.bobo.snake.entities;

import java.awt.Graphics;
import java.awt.Point;

import cn.bobo.snake.util.Global;

/**
 * Created by Leon on 2019/1/13. Copyright © Leon. All rights reserved.
 * Functions: 本次游戏贪吃蛇中的食物类
 */
public class Food extends Point{
	
	
	
	/**增加食物的方法*/
	public void newFood(Point p) {
		this.setLocation(p);
	}
	
	/**
	 * 判断蛇是否吃到了食物
	 * @param snake 蛇类
	 * @return 是/否
	 */
	public boolean isSnakeEatFood(Snake snake) {
		
		System.out.println("判断蛇是否吃到了食物");
		
		//比坐标 坐标重合就代表吃到了 蛇头的坐标和食物的坐标对比
		return this.equals(snake.getHead());
	}
	
	/**食物类显示的方法*/
	public void drawMe(Graphics g) {
		System.out.println("食物类显示的方法");
		
		g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}

}
