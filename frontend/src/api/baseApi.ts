const BASE_URL = 'http://31.129.111.191:8080/'

const get = async (url: string, query: string) => {
	try {
		const response = await fetch(BASE_URL + url + '?' + query, {
			method: 'GET',

		})
		return await response.json()
	} catch (error) {
		throw error
	}
}

export { get }