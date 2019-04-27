//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if(res.code){
          wx.request({
            url: this.globalData.url + '/main/login',
            data: {
              code: res.code,
              appid: "wxbab581961ef84ef7",
              sessionId: wx.getStorageSync('sessionId')
            },
            success: res => {
              res = res.data
              console.log(res)
              wx.setStorageSync('sessionId', res.data)
            }
          })
        } else {
          console.log('登录失败' + res.errMsg)
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
              console.log(res.userInfo)
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            },
            fail: res => {
              wx.showModal({
                title: '警告',
                content: '获取信息失败请重新进入小程序！！！',
                showCancel: false,
                confirmText: '确认'
              })
            }
          })
        }
        // else {
        //   wx.reLaunch({
        //     url: 'pages/login/login',
        //   })
        // }
      }
    })
  },
  globalData: {
    userInfo: null,
    url: "http://106.14.177.245:8088/ziyuan"
  }
})