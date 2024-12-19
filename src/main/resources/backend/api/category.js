// Query list interface
const getCategoryPage = (params) => {
  return $axios({
    url: '/category/page',
    method: 'get',
    params
  })
}

// Edit page reverse query details interface
const queryCategoryById = (id) => {
  return $axios({
    url: `/category/${id}`,
    method: 'get'
  })
}

// Delete the interface of the current column
const deleCategory = (ids) => {
  return $axios({
    url: '/category',
    method: 'delete',
    params: { ids }
  })
}

// Modify the interface
const editCategory = (params) => {
  return $axios({
    url: '/category',
    method: 'put',
    data: { ...params }
  })
}

//New Interface
const addCategory = (params) => {
  return $axios({
    url: '/category',
    method: 'post',
    data: { ...params }
  })
}