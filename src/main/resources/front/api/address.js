//get all addresses
function addressListApi() {
    return $axios({
      'url': '/addressBook/list',
      'method': 'get',
    })
  }

//get the newest address
function addressLastUpdateApi() {
    return $axios({
      'url': '/addressBook/lastUpdate',
      'method': 'get',
    })
}

//add address
function  addAddressApi(data){
    return $axios({
        'url': '/addressBook',
        'method': 'post',
        data
      })
}

//modify address
function  updateAddressApi(data){
    return $axios({
        'url': '/addressBook',
        'method': 'put',
        data
      })
}

//delete address
function deleteAddressApi(params) {
    return $axios({
        'url': '/addressBook',
        'method': 'delete',
        params
    })
}

//query address
function addressFindOneApi(id) {
  return $axios({
    'url': `/addressBook/${id}`,
    'method': 'get',
  })
}

//set default address
function  setDefaultAddressApi(data){
  return $axios({
      'url': '/addressBook/default',
      'method': 'put',
      data
    })
}

//get default address
function getDefaultAddressApi() {
  return $axios({
    'url': `/addressBook/default`,
    'method': 'get',
  })
}