import axios from 'axios'
import { getToken } from '@/utils/auth'

export function getInfor() {
  const result = axios.get('http://localhost:8080/admin/get-infor',{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function updateInfor(data) {
  const result = axios.post('http://localhost:8080/admin/update-infor',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result;
}
