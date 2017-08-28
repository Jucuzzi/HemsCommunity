<template>
	<div v-show="show" @click="showDialog" class="raider-calendar-dialog">
		<div onclick="event.cancelBubble = true" id="total_outer_div">
			<div id="total_div">
	            <div id="top_div">
	            	<div class="sign-record-div">
						<span>签到记录</span>
					</div>
					<div class="calendar-date-div">
						<div id="prev_div" @click="prev">
		            		<img src="../../assets/img/sign/left_arrow.png">&nbsp;
		            	</div>
		            	<div class="calendar-date-text">
							<span v-text="this.showDate" ></span>
						</div>
						<div id="next_div" @click="next">
							<img src="../../assets/img/sign/right_arrow.png"/>
						</div>
					</div>

	            </div>
	           
	            <div class="week-text-div">
	            	<span class="weekend-day">日</span>
	            	<span class="working-day">一</span>
	            	<span class="working-day">二</span>
	            	<span class="working-day">三</span>
	            	<span class="working-day">四</span>
	            	<span class="working-day">五</span>
	            	<span class="weekend-day">六</span>
	            </div>
	            <div class="grey-line-div">
	       
	            </div>
	            <div id="calendar_div">
	            </div>
	        </div>
        </div>
	</div>
</template>

<script>


export default {
	props:['signDateList'],
	
	data(){
		return{
			show:false,
			y: null,
            m: null,
            d: null,
            date: null,
            week: null,
            days: null,
			now : null
		}
	},
	computed: {
		showDate: function () {
		   return this.y+" - "+(this.m+1 < 10 ? "0"+(this.m+1):(this.m+1));
		},
	},
	mounted(){
// 		this.$nextTick(function () {
//	        this.$on('showCalendarDialog', function () {
//	         	alert('showCalendarDialog');
//	        })
//		});
 		this.draw();
	},
	methods: {
		showDialog: function(){
			this.show = !this.show;
		},
		init: function(inyear, inmonth){
                    this.now = new Date();
                    this.y = inyear == null ? this.now.getFullYear() : inyear;
                    this.m = inmonth == null ? this.now.getMonth() : (parseInt(inmonth) - 1);
                    this.d = 1;
                    if (this.date == null) {
                        this.date = new Date(this.y, this.m, this.d);
                    }
					this.y = this.date.getFullYear();
					this.m = this.date.getMonth();
					this.d = this.date.getDate();
			    
              },
        
		prev : function(){
			this.date.setMonth(this.date.getMonth() - 1);
			this.draw();
		},
		next : function(){
			this.date.setMonth(this.date.getMonth() + 1);
			this.draw();
		},
		draw: function(inyear, inmonth){
		
					//初始化
                    this.init(inyear, inmonth);
					//日期属性
                    this.getDateInfo();
					//日历的字串
                    var calendarDivStr = '';
					//title和日历主要字串               
                    calendarDivStr += this.drawCalendar();
					//把日历字串写入层
                    document.getElementById('calendar_div').innerHTML = ""+calendarDivStr;
//					document.getElementById('date_div').innerHTML = this.y + "年" + (this.m + 1) + "月";
		            
			},
				/**
				 * 画出日历
				 */
                drawCalendar: function(){
  
					//算出本月日历总共有好多格子
                    var cot = this.week + this.days;
					//要返回的日历字串
                    var caStr = '';
					//循环拼接
                    for (var i = 0; i < 42; i++) {
                        var content = (i + 1 - this.week);
                        var time;
						//如果是空白日期,里面内容也为空白
                        if (i <= (this.week - 1) || i >= cot){
                            content = "&nbsp;";
                        } else {
                        	  time = this.y+(this.m+1 < 10 ? "0"+(this.m+1):(this.m+1))+
                        		(content < 10 ? "0"+content:content);
                        }
						var backgroundcolor = "";
						var backgroundcolorTwo = "";
						if(this.y == this.now.getFullYear() && this.m == this.now.getMonth() && this.now.getDate() == content){
							backgroundcolor = 
							"background-color : #ececec;";
						}
                        caStr += '<div><span style="'+backgroundcolor+'">';
                        caStr += content;
                      
                        if (time in this.signDateList) {
                            caStr += '<img style="position:absolute;top:8px;left:6px" width="14px" height="12px" src="'+require('../../assets/img/sign/hook.png')+'">';
                            if (this.signDateList[time] == "1") {
                                caStr += '<img style="position:absolute;top:-5px;left:16px" width="10px" height="11px" src="'+require('../../assets/img/sign/package.png')+'">';  	
                            }
                        }
                       caStr += '</span></div>';
                    }
                    console.log('5');      
                    return caStr;
                },
				/**
				 * 获得日期属性
				 */
                getDateInfo: function(){
                    //日历第一天
                    var weeks = [7, 1, 2, 3, 4, 5, 6];
                    //日历天数
                    var days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                    //获得第一天
                    var day = this.date.getDay();
                    //得到第一天
                    this.week = weeks[day];
                    //得到月份
                    //                    var month = ca.date.getMonth() + 1;
                    
                    /********得到日历天数*********/
                    this.days = days[this.m];
                    if (this.m == 1) {
                        if ((this.y % 4 == 0 && this.y % 100 != 0) || this.y % 400 == 0) {
                            this.days++;
                        }
                    }
                    
                }
	}
}
</script>


<style>
.raider-calendar-dialog{
	  position: fixed;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background-color: rgba(0, 0, 0, 0.5);
	  display: flex;
	  display: -webkit-flex;
	  justify-content: center;
	  -webkit-justify-content: center;
	  align-items: center;
	  -webkit-align-items: center;
	  z-index: 100;
}
			#total_outer_div{
				 margin-left:6%;
				 margin-right: 6%;
	
				 display: flex;
				 border-radius: 6px;
				 flex-direction: row;
				 align-items: center;
				 justify-content: center;
				 background: white;	 
			}
/*总层*/
            #total_div {
                width: 100%;
               	display: flex;
				flex-direction: column;
				align-items:center;
				justify-content: center;
            } 
			/*上边的层*/
			#top_div {
                width: 90%;
                height:85px;
                margin-left: 5%;
                margin-right: 5%;
                display: flex;
                flex-direction: column;
				align-items:center;
				justify-content: center;
            } 
			/*上边层里面的设置*/
			#top_div div{
				line-height : 60px;
				vertical-align : middle;
				text-align :center;
			}		
			/*上边层里面的左翻页的层*/		
			#top_div #prev_div{
				width : 22%;
				display: flex;
				flex-direction: row;
				align-items:center;
				margin-left: 3%;
				justify-content: flex-start;				
			}
			/*签到记录*/	
			.sign-record-div{
				width : 50%;
				height: 45px;
				display: flex;
				flex-direction: row;
				align-items:center;
				justify-content: center;
			}	
	
			/*上边层里面的右翻页的层*/	
			#top_div #next_div{
				width : 22%;
				display: flex;
				flex-direction: row;
				align-items:center;
				margin-right: 3%;
				justify-content: flex-end;		
			}	
			/*上边层里面的图片设置*/
			#top_div img{
				width : 20px;
				height : 20px;
			}		
			/*主要显示日历的层*/		
			#calendar_div {
          		padding-left: 4.5%;
          		padding-right: 4.5%;
          		display: flex;
				flex-direction: row;
				align-items:center;
          		flex-wrap:wrap
            } 
			/*主要显示日历的层里面的层*/
			#calendar_div div {
                width: 14.28%;
                height: 40px;
                font-size: 12px;
                display: flex;
				flex-direction: row;
				align-items:center;
				justify-content: center;
            }
            #calendar_div span{
            	width: 22px;
            	height: 22px;
            	border-radius: 11px;
            	display: flex;
            	position:relative;
				flex-direction: row;
				align-items:center;
				justify-content: center;
				
            }
	.week-text-div{
		width: 90%;
		margin-left: 5%;
		margin-right: 5%;
		height: 40px;
		display: flex;
		flex-direction: row;
		align-items:center;
		justify-content: center;
	}
	.weekend-day{
		width: 13%;
		color: #999999;
	}
	.working-day{
		width: 14.28%;
	}
	.grey-line-div{
		width: 100%;
		height: 1px;
		background-color: #DDDDDD;
		
	}
	.calendar-date-div{
		height: 40px;
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items:center;
		justify-content: center;
	}
		/*上边层里面的年月显示的层*/	
	.calendar-date-text{
		width : 50%;
		height: 40px;
		font-size:14px ;
		display: flex;
		flex-direction: row;
		align-items:center;
		justify-content: center;
	}
</style>