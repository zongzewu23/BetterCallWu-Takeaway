// Query list data
const getDishPage = (params) => {
  return $axios({
    url: '/dish/page',
    method: 'get',
    params
  })
}

// Delete data interface
const deleteDish = (ids) => {
  return $axios({
    url: '/dish',
    method: 'delete',
    params: { ids }
  })
}

// Modify data interface
const editDish = (params) => {
  return $axios({
    url: '/dish',
    method: 'put',
    data: { ...params }
  })
}

//Add data interface
const addDish = (params) => {
  return $axios({
    url: '/dish',
    method: 'post',
    data: { ...params }
  })
}

// Query details interface
const queryDishById = (id) => {
  return $axios({
    url: `/dish/${id}`,
    method: 'get'
  })
}

// Get dish classification list
const getCategoryList = (params) => {
  return $axios({
    url: '/category/list',
    method: 'get',
    params
  })
}

// Query dish list interface
const queryDishList = (params) => {
  return $axios({
    url: '/dish/list',
    method: 'get',
    params
  })
}

// File down preview
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// Start and stop sales ---Batch start and stop sales interface
const dishStatusByStatus = (params) => {
  return $axios({
    url: `/dish/status/${params.status}`,
    method: 'post',
    params: { ids: params.id }
  })
}