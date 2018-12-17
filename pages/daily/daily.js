var app = getApp();
var utils = require('../../utils/util.js')
Page({
  data: {
    year:null,
    day:null,
    month:null,
    dailyId:null,
    peoId:null,
    time:null,
    place:null,
    isSuccess:false,
    dateArr: ['1','2','3'],
    idNum:0,
    dailyDetail: [],
    winHeight: "600",//窗口高度
    currentTab: 0, //预设当前项的值
    scrollLeft: 0, //tab标题的滚动条位置
 
  },
  // 滚动切换标签样式
  switchTab: function (e) {
    
    this.setData({
      currentTab: e.detail.current,
     
    });
    console.log(currentTab);
    this.checkCor();
  },
  // 点击标题切换当前页时改变样式
  swichNav: function (e) {
    var cur = e.target.dataset.current;
    if (this.data.currentTaB == cur) { return false; }
    else {
      this.setData({
        currentTab: cur
      })
    }
  },
  //判断当前滚动超过一屏时，设置tab标题滚动条。
  checkCor: function () {
    if (this.data.currentTab > 4) {
      this.setData({
        scrollLeft: 300
      })
    } else {
      this.setData({
        scrollLeft: 0
      })
    }
  },
  onLoad: function (e) {
    console.log("date:"+utils.formatDate(new Date()))
    var that = this;
    //  高度自适应
    this.setData({
      year: e.year,
      day: e.day,
      month: e.month
    }) 
    wx.request({
      url: utils.webUrl+'student/daily/'+'2018-12-05',
      header:{
        'content-type':"application/x-www-form-urlencoded",
        'User-Token': wx.getStorageSync('token')
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data.data[0]);
        var sign={};
        var i=0;
        let dailysign=that.data.dailyDetail;
        while (res.data.data[i] != null)
        {
          sign.signName=res.data.data[i].signName.charAt(0);
          sign.signInstanceId=res.data.data[i].signInstanceId;
          sign.placeName=res.data.data[i].placeName;
          sign.startTime=res.data.data[i].startTime;
          sign.endTime=res.data.data[i].endTime;
          sign.date=res.data.data[i].date;
          sign.status=res.data.data[i].status;
          dailysign.push(sign);
          i++;
          
        }
        that.setData({dailyDetail:dailysign});
        console.log(res.data.data[1]==null)
      },
      fail: function (res) {
        console.log(".....fail.....");
      }
    })
    wx.getSystemInfo({
      success: function (res) {
        var clientHeight = res.windowHeight,
          clientWidth = res.windowWidth,
          rpxR = 750 / clientWidth;
        var calc = clientHeight * rpxR - 180;
        console.log(calc)
        that.setData({
          winHeight: calc
        });
      }
    })
  },
  //获取某日所有的打卡名（allIdName[]），以及打卡id（独一无二的）(dailyId)，打卡数量(idNum)，传递给后台日期,人的id
sign:function(e){
  console.log(e.currentTarget.dataset.signinstanceid)
wx.navigateTo({
  url: '../sign/sign?id='+e.currentTarget.dataset.signinstanceid,
})
},
 

  footerTap: app.footerTap
})
