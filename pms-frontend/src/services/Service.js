import axios from 'axios'

const protocol = process.env.VUE_APP_BACKEND_PROTOCOL;
const host = process.env.VUE_APP_BACKEND_HOST;
const port = process.env.VUE_APP_BACKEND_PORT;
const apiRoot = process.env.VUE_APP_BACKEND_ROOT;


const baseURL = `${protocol}://${host}:${port}/${apiRoot}`;
console.log(`Base url is ${baseURL}`);

export default axios.create({
    baseURL
});