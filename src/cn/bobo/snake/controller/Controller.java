package cn.bobo.snake.controller;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import cn.bobo.snake.entities.Food;
import cn.bobo.snake.entities.Ground;
import cn.bobo.snake.entities.Snake;
import cn.bobo.snake.listener.SnakeListener;
import cn.bobo.snake.util.Global;
import cn.bobo.snake.view.GamePanel;

import static cn.bobo.snake.entities.Snake.UP;
import static cn.bobo.snake.entities.Snake.DOWN;
import static cn.bobo.snake.entities.Snake.LEFT;
import static cn.bobo.snake.entities.Snake.RIGHT;

/**
 * Created by Leon on 2019/1/13. Copyright © Leon. All rights reserved.
 * Functions: 本次游戏贪吃蛇的的 控制类 MVC 中的  C
 */
public class Controller extends KeyAdapter implements SnakeListener {
	
	//蛇类
	private Snake snake;
	
	//食物类
	private Food food;
	
	//墙 石头 类
    private	Ground ground;
    
    //展示内容的面板
    private GamePanel gamepanel;
	
    
	/**
	 * @param snake  蛇类
	 * @param food   食物类
	 * @param ground   墙 石头 类
	 * @param gamepanel  展示内容的面板
	 */
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamepanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamepanel = gamepanel;
	}


	/**
	 * keyPressed  当用户按下键盘上的按钮会触发这个方法
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		//监听用户对键盘按钮的点击事件
		int key = e.getKeyCode();
		
		switch (key) {
		case KeyEvent.VK_UP: //用户按下了↑
			snake.changeDirection(UP);
			break;

		case KeyEvent.VK_DOWN://用户按下了↓
			snake.changeDirection(DOWN);
			break;
			
		case KeyEvent.VK_LEFT://用户按下了左
			snake.changeDirection(LEFT);
			break;
			
		case KeyEvent.VK_RIGHT://用户按下了右
			snake.changeDirection(RIGHT);
			break;
		}
		
		super.keyPressed(e);
	}

	
	@Override
	public void snakeMoved(Snake snake) {
		
		//判断蛇是否吃到食物
		if (food.isSnakeEatFood(snake)) {
			//调用蛇吃到食物的方法
			snake.eatFood();
			//原来的食物被吃掉了再生成一个新的食物(随机坐标)
			food.newFood(ground.getPoint());
		}
		
		//判断蛇是否吃到石头
		if (ground.isSnakeEatRock(snake)) {
			//调用蛇死掉的方法
			snake.die();
		}
		
		//判断蛇是否吃到自己
		if (snake.isEatBody()) {
			//调用蛇死掉的方法
			snake.die();
		}
		
		//让面板重新显示
		gamepanel.display(snake, food, ground);
		
	}
	
	
	
	//开始新游戏的方法
	public void newGame() {
		//蛇开始动起来
		snake.start();
		
		//添加一个食物（坐标是随机的）
		food.newFood(ground.getPoint());
	}

}
