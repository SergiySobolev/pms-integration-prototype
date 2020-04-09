import Service from "./Service"

const practiceInfo = "practiceinfo";

export default {

    getPractices() {
        console.log('Fetching practices info');
        return Service.get(`${practiceInfo}`);
    },
    getPractice(practiceId) {
        console.log(`Fetching practice info for id ${practiceId}`);
        return Service.get(`${practiceInfo}/${practiceId}`);
    }
}