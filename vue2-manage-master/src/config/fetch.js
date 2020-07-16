import { baseUrl } from './env'
import {deepClone} from '@/utils'

export default async(url = '', data = {}, type = 'GET', method = 'fetch',other = '') => {
	type = type.toUpperCase();
	url = baseUrl + url;

    if(other!='file') data=deepClone(data)

	if (type == 'GET') {
		let dataStr = ''; //数据拼接字符串
		Object.keys(data).forEach(key => {
			dataStr += key + '=' + data[key] + '&';
		})

		if (dataStr !== '') {
			dataStr = dataStr.substr(0, dataStr.lastIndexOf('&'));
			url = url + '?' + dataStr;
		}
    }


          // 分页参数param, 查询参数data
        if (url.endsWith('/list')) {

            const params = {
              pageSize: data.limit,
              pageNumber: data.page,
              sorts: data.sort,
              searchKey: data.searchKey
            }
            delete data.limit
            delete data.page
            delete data.sort
            delete data.searchKey



            let dataStr = ''; //数据拼接字符串
            Object.keys(params).forEach(key => {
                if(params[key]) dataStr += key + '=' + params[key] + '&';
            })

            if (dataStr !== '') {
                dataStr = dataStr.substr(0, dataStr.lastIndexOf('&'));
                url = url + '?' + dataStr;
            }

            // 过滤data中值是空的参数
            if (Object.keys(data).length > 0) {
              for (var key in data) {
                if (data[key] === '') {
                  delete data[key]
                }
              }
            }
            if (Object.keys(data).length === 0) {
              data = null
            }
        }


	if (window.fetch && method == 'fetch') {
		let requestConfig = {
			credentials: 'include',
			method: type,
			headers: {
				'Accept': other=='file'?'application/x-www-form-urlencoded':'application/json',
				'Content-Type': other=='file'?'application/x-www-form-urlencoded':'application/json'
			},
			mode: "cors",
			// cache: "force-cache"
		}

		if (type == 'POST'||type == 'PUT') {
            if(other=='file'){
                Object.defineProperty(requestConfig, 'body', {
                    value: data
                })
            }else{
                Object.defineProperty(requestConfig, 'body', {
                    value: JSON.stringify(data)
                })
            }
		}

		try {
			const response = await fetch(url, requestConfig);
			const responseJson = await response.json();
			return responseJson
		} catch (error) {
			throw new Error(error)
		}
	} else {
		return new Promise((resolve, reject) => {
			let requestObj;
			if (window.XMLHttpRequest) {
				requestObj = new XMLHttpRequest();
			} else {
				requestObj = new ActiveXObject;
			}

			let sendData = '';
			if (type == 'POST') {
				sendData = JSON.stringify(data);
			}

			requestObj.open(type, url, true);
			requestObj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			requestObj.send(sendData);

			requestObj.onreadystatechange = () => {
				if (requestObj.readyState == 4) {
					if (requestObj.status == 200) {
						let obj = requestObj.response
						if (typeof obj !== 'object') {
							obj = JSON.parse(obj);
						}
						resolve(obj)
					} else {
						reject(requestObj)
					}
				}
			}
		})
	}
}
