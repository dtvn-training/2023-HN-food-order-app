import request from '@/utils/request'
import { getToken } from'@/utils/auth'
import axios from 'axios'

const state = {
  token: getToken()
}
export function updateMember(data) {
  console.log(data)
  const result = axios.post('http://localhost:8080/admin/update-member',data ,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  });
  console.log(result)
  return result
}


export function getMembers() {
  const result = axios.get('http://localhost:8080/admin/listmember', {
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  });
  console.log(result)
  return result
}

export function createMember(user) {

  const result = axios.post('http://localhost:8080/api/createmember',user ,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  });
  console.log(result)
  return result
}


