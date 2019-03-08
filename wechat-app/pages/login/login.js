// pages/login/login.js
//获取应用实例
const app = getApp()

Page({
  data: {
    isSigin: false,
    channel: '',
    // 授权登录
    authSuccess: true,
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
      wx.switchTab({
        url: '/pages/index/index'
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        wx.switchTab({
          url: '/pages/index/index'
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
          wx.switchTab({
            url: '/pages/index/index'
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    if(e.detail.userInfo) {
      app.globalData.userInfo = e.detail.userInfo
      this.setData({
        userInfo: e.detail.userInfo,
        hasUserInfo: true
      })
      // 保存用户信息
      wx.request({
        url: app.globalData.url + '/main/getUserInfo',
        method: "POST",
        header: {
          "content-type": "application/json",
          "sessionId": wx.getStorageSync('sessionId'),
          "encryptedData": e.detail.encryptedData,
          "iv": e.detail.iv
        },
        data: e.detail.userInfo,
        success: res => {
          res = res.data
          console.log(res);
          // 跳转进首页
          if (res.code == "0000") {
            wx.switchTab({
              url: '/pages/index/index'
            })
          }
          
        }
      })
      // 
    } else {
      wx.showModal({
        title: '警告',
        content: '请授权后再进入小程序！！！',
        showCancel:false,
        confirmText:'返回授权'
      })
    }
  }
})
