(function (win) {
// Set default headers for all axios requests
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';

// Create an axios instance
const service = axios.create({
  // The base URL for all requests, representing the common part of the request URL
  baseURL: '/',
  // Timeout setting in milliseconds, requests will be canceled if they exceed this time
  timeout: 1000000
});

// Request interceptor
service.interceptors.request.use(config => {
  // Check if a token needs to be added to the request
  // const isToken = (config.headers || {}).isToken === false;
  // if (getToken() && !isToken) {
  //   // If a token exists and the request requires it, add the token to the Authorization header
  //   config.headers['Authorization'] = 'Bearer ' + getToken();
  // }

  // Map GET requests' params
    if (config.method === 'get' && config.params) {
      let url = config.url + '?';
      for (const propName of Object.keys(config.params)) {
        const value = config.params[propName];
        var part = encodeURIComponent(propName) + "=";
        if (value !== null && typeof(value) !== "undefined") {
          if (typeof value === 'object') {
            for (const key of Object.keys(value)) {
              let params = propName + '[' + key + ']';
              var subPart = encodeURIComponent(params) + "=";
              url += subPart + encodeURIComponent(value[key]) + "&";
            }
          } else {
            url += part + encodeURIComponent(value) + "&";
          }
        }
      }
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
    }
    return config
  }, error => {
      Promise.reject(error)
  })

  // Response interceptor
  service.interceptors.response.use(
    res => {
      // Log the response for debugging purposes
      console.log('---Response Interceptor---', res);

      // Check if the response indicates the user is not logged in
      if (res.data.code === 0 && res.data.msg === 'NOTLOGIN') {
        // Redirect the user to the login page
        window.top.location.href = '/front/page/login.html';
      } else {
        // Return the response data if there are no issues
        return res.data;
      }
    },
    error => {
      // Extract the error message
      let { message } = error;

      // Handle different types of errors with user-friendly messages
      if (message == "Network Error") {
        message = "Backend interface connection error";
      } else if (message.includes("timeout")) {
        message = "System interface request timeout";
      } else if (message.includes("Request failed with status code")) {
        // Extract the HTTP status code and append it to the error message
        message = "System interface error with status code " + message.substr(message.length - 3);
      }

      // Use Vant's Notify component to display a warning notification
      window.vant.Notify({
        message: message,
        type: 'warning',
        duration: 5 * 1000 // Display for 5 seconds
      });
      //window.top.location.href = '/front/page/no-wify.html'
      return Promise.reject(error)
    }
  )
  win.$axios = service
})(window);
