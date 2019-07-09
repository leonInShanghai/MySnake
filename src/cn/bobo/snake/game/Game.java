package cn.bobo.snake.game;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cn.bobo.snake.controller.Controller;
import cn.bobo.snake.entities.Food;
import cn.bobo.snake.entities.Ground;
import cn.bobo.snake.entities.Snake;
import cn.bobo.snake.util.Global;
import cn.bobo.snake.view.GamePanel;

/**
 * Created by Leon on 2019/1/13. Copyright © Leon. All rights reserved.
 * Functions: 本次游戏贪吃蛇的 程序main函数
 */
public class Game {

	
	/**程序的main函数*/
	public static void main(String[] args) {
		
		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamepanel = new GamePanel();
		
		Controller controller = new Controller(snake, food, ground, gamepanel);
		
		JFrame frame = new JFrame();
		//设置默认关闭的时候就退出程序
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		//显示面板 的宽： 一个格子的 宽度 *行数  高：一个格子的宽度 *列数 
		gamepanel.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);
		//窗口体 的宽： 一个格子的 宽度 *行数  高：一个格子的宽度 *列数   注意窗口体要比面板大一些
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 10, Global.HEIGHT * Global.CELL_SIZE + 35);
		
		frame.add(gamepanel,BorderLayout.CENTER);
		
		//设置自定义的icon
		ImageIcon icon = new ImageIcon("img/icon.jpg");
		Image img = icon.getImage();
		frame.setIconImage(img);
		//设置标题
		frame.setTitle("波波贪吃蛇");
		//是否允许用户拖拽改变窗口体的大小 不写为true
		frame.setResizable(false);
		
		//注册监听器 (设置controller为gamepanel 的监听器代理人)
		gamepanel.addKeyListener(controller);
		//注册监听器 (设置controller为snake 的监听器代理人)
		snake.addSnakeListener(controller);
		
		
		//给frame 设置键盘事件监听器 代理的对象是 controller
		frame.addKeyListener(controller);
		
		//让frame显示出来
		frame.setVisible(true);
		
		//开始新游戏
		controller.newGame();
	}
	
}
