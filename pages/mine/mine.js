// pages/mine/mine.js
var utils = require('../../utils/util.js')
Page({
  /**
   * 页面的初始数据
   */
  data: {
    menuitems: [
      { text: '个人信息', url: '../personal/personal', icon: '../../images/课表.png' },
      { text: '学号绑定', url: '../binding/binding', icon: '../../images/积分.png', tips: '' },
      { text: '人脸注册', url: '../register/register', icon: '../../images/积分.png', tips: '' },
      { text: '关于我们', url: '../about/about', icon: '../../images/礼品中心.png', tips: '' },
      { text: '信息反馈', url: '../suggest/suggest', icon: '../../images/我的礼品.png', tips: '' }
    ],
    imgUrl:'../../images/我的礼品.png',
    nick:'登录'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   this.setData({
     imgUrl: wx.getStorageSync('imgUrl'),
     nick:wx.getStorageSync('name')
   })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})