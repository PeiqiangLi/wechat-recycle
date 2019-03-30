// pages/cart/cart.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    selectAllStatus: false,
    totalPrice: 0.00,
    checkboxItems: [],
  },
  checkboxChange: function (e) {
    console.log('checkbox发生change事件，携带id值为：', e.detail.value);

    var checkboxItems = this.data.checkboxItems, values = e.detail.value;
    var money = 0.00;
    for (var i = 0, lenI = checkboxItems.length; i < lenI; ++i) {
      checkboxItems[i].checked = false;

      for (var j = 0, lenJ = values.length; j < lenJ; ++j) {
        if (checkboxItems[i].id == values[j]) {
          checkboxItems[i].checked = true;
          money = money + checkboxItems[i].money;
          break;
        }
      }
    }

    this.setData({
      checkboxItems: checkboxItems,
      totalPrice: money
    });
  },

  // 全选
  selectAll: function () {
    var checkboxItems = this.data.checkboxItems;
    var selectAllStatus = !this.data.selectAllStatus;
    var money = 0.00;
    for (var j = 0, lenJ = checkboxItems.length; j < lenJ; ++j) {
      checkboxItems[j].checked = selectAllStatus;
      money = money + checkboxItems[j].money;
    }
    if (!selectAllStatus) {
      money = 0.00;
    }

    this.setData({
      selectAllStatus: selectAllStatus,
      checkboxItems: checkboxItems,
      totalPrice: money
    });
  },

 // 删除
  deleteWaste: function (e) {
    var _this = this;
    wx.showModal({
      cancelText: '取消',
      confirmText: '确认',
      title: '警告',
      content: '删除后不可恢复，请确认！',
      success(res) {
        if (res.confirm) {
          var current = e.currentTarget.dataset.index;
          var checkboxItems = _this.data.checkboxItems;
          console.log(checkboxItems[current].id);
          wx.request({
            url: app.globalData.url + '/cart/deleteCart',
            data: {
              id: checkboxItems[current].id,
            },
            success: res => {
              res = res.data
              console.log(res);
              checkboxItems.splice(current, 1)
              if (res.code == "0000") {
                _this.setData({
                  checkboxItems: checkboxItems
                })
              }
            }
          })
        } 
      }
    })
    
  },

 // 下一步
  toBuy: function () {
    var checkboxItems = this.data.checkboxItems;
    var waste = new Array();
    for (var j = 0, lenJ = checkboxItems.length; j < lenJ; ++j) {
      if (checkboxItems[j].checked == true) {
        waste.push(checkboxItems[j])
      }
    }
    if (waste.length > 0) {
      var wasteJson = JSON.stringify(waste)
      var totalPrice = this.data.totalPrice
      wx.navigateTo({
        url: '../buy/buy?waste=' + wasteJson + '&totalPrice=' + totalPrice,
      })
    } else {
      wx.showModal({
        title: '警告',
        content: '请勾选需要回收的物品！',
        confirmColor: '#3296C8',
        showCancel: false,
      })
    }
    
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
    wx.request({
      url: app.globalData.url + '/cart/getCart',
      data: {
        sessionId: wx.getStorageSync('sessionId'),
      },
      success: res => {
        res = res.data
        console.log(res);
        var cart = new Array();
        for (var index in res.data) {
          cart.push(res.data[index]);
        }
        this.setData({
          selectAllStatus: false,
          totalPrice: 0.00,
          checkboxItems: cart,
        })
      }
    })
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