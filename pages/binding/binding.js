var utils = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  //绑定用的
  formSubmit:function(e){
    console.log(e.detail.value)

    wx.request({
      url: utils.webUrl+'bangding',
      header: { 'User-Token': wx.getStorageSync('token')},
      data: {
        studentId: e.detail.value.stuid,
        name: e.detail.value.name,
        password: e.detail.value.password,
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data);
       // console.log('stuid=' + e.detail.value.stuid)
      
        if (res.data.code == 10000) {
          wx.setStorageSync('studentId', e.detail.value.stuid);
          wx.showToast({
            title: '绑定成功',
            icon: 'succes',
            duration: 1000,
            mask: true
          })
          
          setTimeout(function () {
            wx.redirectTo({
              url: "/pages/mine/mine",
            })
          }, 1000)
          
        }

      },
      fail: function () {
        wx.showToast({
          title: '绑定失败',
          icon: 'fail',
          duration: 1000,
          mask: true
        })
        // fail
      },
      complete: function () {
        // complete
      }
    })


  }
})