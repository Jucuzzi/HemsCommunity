package com.telek.service;

public interface LuckyDrawService {

	public String hemsCircleQueryPrizeDrawMain(int userId);
	public String hemsCircleQueryPrizeDrawHistory(int userId);
	public String hemsCirclePrizeDraw(int userId);
	public String queryHemsCircleSignIn(int userId);
	public String queryemsCircleQueryPointsMain(int userId);
	public String queryemsHemsCircleQuerySignInState(int userId);
}
