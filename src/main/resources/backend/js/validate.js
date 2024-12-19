
function isValidUsername (str) {
  return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal (path) {
  return /^(https?:|mailto:|tel:)/.test(path);
}

function isCellPhone (val) {
  if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
    return false
  } else {
    return true
  }
}

//校验账号
function checkUserName (rule, value, callback){
  if (value == "") {
    callback(new Error("Please enter your account or username"))
  } else if (value.length > 20 || value.length <3) {
    callback(new Error("The account should be 3-20 characters long."))
  } else {
    callback()
  }
}

//Verify Name
function checkName (rule, value, callback){
  if (value == "") {
    callback(new Error("Please enter your name"))
  } else if (value.length > 12) {
    callback(new Error("The length should be 1-12"))
  } else {
    callback()
  }
}

function checkPhone(rule, value, callback) {
  //(XXX) XXX-XXXX, XXX-XXX-XXXX 或 XXXXXXXXXX
  let phoneReg = /^(?:\+1[-.\s]?)?\(?\d{3}\)?[-.\s]?\d{3}[-.\s]?\d{4}$/;
  if (value === "") {
    callback(new Error("Please enter your phone number"));
  } else if (!phoneReg.test(value)) {
    callback(new Error("Please enter a valid US phone number!"));
  } else {
    callback();
  }
}



function validID (rule,value,callback) {
  // SSN format is XXX-XX-XXXX
  let reg = /^(?!000|666|9\d{2})\d{3}-(?!00)\d{2}-(?!0000)\d{4}$/;
  if(value == '') {
    callback(new Error('Please Enter SSN'))
  } else if (reg.test(value)) {
    callback()
  } else {
    callback(new Error('SSN format is incorrect!'))
  }
}