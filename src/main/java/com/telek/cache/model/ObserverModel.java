package com.telek.cache.model;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者对象
 * 
 * @author Xugl
 *
 * @date 2017年5月12日 上午9:44:54
 */
public abstract class ObserverModel implements Observer {

	public abstract void update(Observable o, Object arg);

	public abstract void initData();

	public abstract void clearAll();
}
