import axios from "axios";

const baseURL = "http://localhost:8081";

export default {
  login(dados) {
    return axios.post(`${baseURL}/login`, dados);
  },
  refresh(refresh_token) {
    return axios.post(
      `${baseURL}/oauth/access_token`,
      {
        grant_type: "refresh_token",
        refresh_token,
      },
      {
        headers: {
          "content-type": "application/x-www-form-urlencoded",
        },
      }
    );
  },
};
