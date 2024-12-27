//Submit Order
function  addOrderApi(data){
    return $axios({
        'url': '/order/submit',
        'method': 'post',
        data
      })
}

//Check all orders
function orderListApi() {
  return $axios({
    'url': '/order/list',
    'method': 'get',
  })
}

//Pagination order query
function orderPagingApi(data) {
  return $axios({
      'url': '/order/userPage',
      'method': 'get',
      params:{...data}
  })
}

//One more order
function orderAgainApi(data) {
  return $axios({
      'url': '/order/again',
      'method': 'post',
      data
  })
}