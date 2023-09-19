import axios from "axios";

export default axios.create({
    headers: {
        "Content-type": "application/json"
    },
    baseURL: "http://localhost:8080/"
});
