package cn.bobo.snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cn.bobo.snake.entities.Food;
import cn.bobo.snake.entities.Ground;
import cn.bobo.snake.entities.Snake;
import cn.bobo.snake.util.Global;

/**
 * Created by Leon on 2019/1/13. Copyright © Leon. All rights reserved.
 * Functions: 本次游戏贪吃蛇 的显示面板 类  MVC 中的 view层
 */
public class GamePanel extends JPanel {

	//蛇类
	private Snake snake;
	
	//食物类
	private Food food;
	
	//墙 石头 类
	private Ground ground;
	
	/**显示游戏面板*/
	public void display(Snake snake,Food food,Ground ground) {
		
		System.out.println("显示游戏面板");
		
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		
		//调用这里 最终会调用paintComponent
		this.repaint();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		// 重新显示(下面的代码打开会自动擦除原来的画布)
		//super.paintComponent(g);
		
		//擦除原来的画布
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);
		
		//画波波开源 的logo  60  和 57 分别是图片的宽高
		ImageIcon icon = new ImageIcon("img/clear_logo.png");
		Image img = icon.getImage();
		int boboLogoX = (int)Math.round(Global.WIDTH * Global.CELL_SIZE * 0.5 - 60 * 0.5);
		int boboLogoY = (int)Math.round(Global.WIDTH * Global.CELL_SIZE * 0.5 - 57 * 0.5);
		//最后一个参数 obser观察者 这里传了null
		g.drawImage(img,boboLogoX, boboLogoY,60, 57, null);
		
		//避免空指针异常
		if (this.snake != null && this.food != null && this.ground != null) {
			//画 石头 墙 
			this.ground.drawMe(g);
			//画蛇
			this.snake.drawMe(g);
			//画食物
			this.food.drawMe(g);
		}
		
	}
}
