package com.telek.cache.model;

import java.util.Observable;
import java.util.Vector;

/**
 * Description: CacheModel.java Create on 2013-2-17 上午9:16:04
 * 
 * @author wm
 * @version 1.0 Copyright (c) 2013 telek. All Rights Reserved.
 */
public class CacheModel extends Observable {
	private Vector<ObserverModel> observerVector = new Vector<ObserverModel>();

	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	public synchronized void addObserver(ObserverModel o) {
		observerVector.add(o);
		super.addObserver(o);
	}

	public void initAllCacheData() {
		Object[] arrLocal;
		synchronized (this) {
			arrLocal = observerVector.toArray();
		}
		for (int i = 0; i <= arrLocal.length - 1; i++) {
			((ObserverModel) arrLocal[i]).initData();
		}
	}

	public int getObserverVectorSize() {
		return observerVector.size();
	}
}
