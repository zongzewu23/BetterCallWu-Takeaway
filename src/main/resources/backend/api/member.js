function getMemberList (params) {
  return $axios({
    url: '/employee/page',
    method: 'get',
    params
  })
}

//Modify ---Enable and disable interface
function enableOrDisableEmployee (params) {
  return $axios({
    url: '/employee',
    method: 'put',
    data: { ...params }
  })
}

// Add---Add employee
function addEmployee (params) {
  return $axios({
    url: '/employee',
    method: 'post',
    data: { ...params }
  })
}

// Edit --- Add employee
function editEmployee (params) {
  return $axios({
    url: '/employee',
    method: 'put',
    data: { ...params }
  })
}

// Modify the page reverse query details interface
function queryEmployeeById (id) {
  return $axios({
    url: `/employee/${id}`,
    method: 'get'
  })
}