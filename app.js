//app.js
var utils = require('./utils/util.js')
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success(res) {
        var code = res.code;//发送给服务器的code
      //  console.log(res)
        if (code) {
          console.log('code:'+code)
          //发起网络请求
          wx.request({
            url: utils.webUrl+'onLogin',
            method:'POST',
            data: {
              code: res.code  
            },
              success(res) {
          //     console.log(res)
                wx.setStorageSync('studentId',res.data.data.studentId);
                wx.setStorageSync('token', res.data.data.token);
             //   console.log(res)
                wx.showToast({
                  title: '自动登录成功！',
                  icon: 'success',
                  duration: 1000,
                  mask: true
                })
            },
            fail(res){
              wx.showToast({
                title: '登录失败！',
                image: '/images/images/icon/x.png',
                duration: 1000,
                mask: true
              })
            }
          })
        } else {
          console.log('登录失败！' + res.errMsg)
          wx.showToast({
            title: '登录失败！',
            image: '/images/images/icon/x.png',
            duration: 1000,
            mask: true
          })
        }
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null
  }
})