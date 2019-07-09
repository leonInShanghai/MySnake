package cn.bobo.snake.listener;

import cn.bobo.snake.entities.Snake;

/**
 * Created by Leon on 2019/1/13. Copyright © Leon. All rights reserved.
 * Functions: 传递点击事件的接口 
 */
public interface SnakeListener {
	
	void snakeMoved(Snake snake);
	
}
