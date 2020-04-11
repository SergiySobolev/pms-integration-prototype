import Service from "./Service"

const practice = "practice";
const departments = "departments";
const providers = "providers";

export default {

    getPractices() {
        console.log('Fetching practices info');
        return Service.get(`${practice}`);
    },
    getPractice(practiceId) {
        console.log(`Fetching practice info for id ${practiceId}`);
        return Service.get(`${practice}/${practiceId}`);
    },
    getDepartments(practiceId) {
        console.log(`Fetching departments for practice #${practiceId}`);
        return Service.get(`${practice}/${practiceId}/${departments}`);
    },
    getProviders(practiceId) {
        console.log(`Fetching providers for practice #${practiceId}`);
        return Service.get(`${practice}/${practiceId}/${providers}`);
    }
}