import { defineStore } from "pinia";
import authService from "src/services/authService";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    access_token: null,
    roles: null,
    username: null,
  }),

  getters: {
    getAcessToken: (state) => {
      return state.access_token;
    },
    getRoles: (state) => {
      return state.roles;
    },
    getUsername: (state) => {
      return state.username;
    },
  },

  actions: {
    async login(username, password) {
      try {
        const { data } = await authService.login({ username, password });
        this.setUser(data);
        localStorage.setItem("access_token", data.access_token);
        localStorage.setItem("refresh_token", data.refresh_token);
      } catch (error) {
        if (error) throw error;
      }
    },
    async logout() {
      try {
        localStorage.setItem("access_token", "");
        localStorage.setItem("refresh_token", "");
        this.clearUser();
      } catch (error) {
        if (error) throw error;
      }
    },
    setUser({ access_token, roles, username }) {
      if (access_token) this.access_token = access_token;
      if (roles) this.roles = roles;
      if (username) this.username = username;
    },

    clearUser() {
      this.access_token = null;
      this.roles = null;
      this.username = null;
    },
  },
  persist: true,
});
