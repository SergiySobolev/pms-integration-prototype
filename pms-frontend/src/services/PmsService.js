import Service from "./Service"

export default {
    getPractices() {
        console.log('Fetching practice info');
        const data = Service.get("practiceinfo");
        return data;
    }
}