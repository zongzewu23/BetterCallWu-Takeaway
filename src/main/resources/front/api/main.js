//Get all the dishes categories
function categoryListApi() {
    return $axios({
      'url': '/category/list',
      'method': 'get',
    })
  }

//Get the dishes corresponding to the dish category
function dishListApi(data) {
    return $axios({
        'url': '/dish/list',
        'method': 'get',
        params:{...data}
    })
}

//Get the set meal corresponding to the dish category
function setmealListApi(data) {
    return $axios({
        'url': '/setmeal/list',
        'method': 'get',
        params:{...data}
    })
}

//Get the collection of items in the shopping cart
function cartListApi(data) {
    return $axios({
        'url': '/shoppingCart/list',
        //'url': '/front/cartData.json',
        'method': 'get',
        params:{...data}
    })
}

//Add items to cart
function  addCartApi(data){
    return $axios({
        'url': '/shoppingCart/add',
        'method': 'post',
        data
      })
}

//Modify items in the shopping cart
function  updateCartApi(data){
    return $axios({
        'url': '/shoppingCart/sub',
        'method': 'post',
        data
      })
}

//Delete items from shopping cart
function clearCartApi() {
    return $axios({
        'url': '/shoppingCart/clean',
        'method': 'delete',
    })
}

//Get all the dishes in the set menu
function setMealDishDetailsApi(id) {
    return $axios({
        'url': `/setmeal/dish/${id}`,
        'method': 'get',
    })
}


