// Query list page interface
const getOrderDetailPage = (params) => {
  return $axios({
    url: '/order/page',
    method: 'get',
    params
  })
}

// View Interface
const queryOrderDetailById = (id) => {
  return $axios({
    url: `/orderDetail/${id}`,
    method: 'get'
  })
}

// Cancel, dispatch, and complete interfaces
const editOrderDetail = (params) => {
  return $axios({
    url: '/order',
    method: 'put',
    data: { ...params }
  })
}
