import axios from 'axios'

const baseURL = import.meta.env.DEV
    ? `http://${window.location.hostname}:8080`
    : ''

const api = axios.create({
    baseURL,
})

export default api