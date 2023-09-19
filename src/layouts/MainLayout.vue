<template>
  <q-layout view="hhh Lpr fff">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          aria-label="Menu"
          dense
          flat
          icon="menu"
          round
          @click="toggleLeftDrawer"
        />
        <q-toolbar-title></q-toolbar-title>

        <div class="q-pa-md">
          <q-tooltip :offset="[10, 10]" anchor="center left" self="center right"
          >Meu Usuário
          </q-tooltip
          >
          <q-btn-dropdown icon="person" unelevated>
            <q-list>
              <router-link
                style="color: inherit; text-decoration: none"
                to="/usuarioPerfil"
              >
                <q-item v-close-popup clickable @click="onItemClick">
                  <q-item-section>
                    <q-item-label>Perfil</q-item-label>
                  </q-item-section>
                </q-item>
              </router-link>

              <q-item v-close-popup clickable @click="logout">
                <q-item-section>
                  <q-item-label>Logout</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </div>

        <div style="padding: 20px"></div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      bordered
      show-if-above
      style="color: #43744e"
    >
      <q-list>
        <q-item-label header>
          <img
            alt="Logo"
            height="180"
            src="~assets/logoUfraSem.png"
            style="padding: 8px; margin-left: 5px"
            width="260"
          />
        </q-item-label>

        <EssentialLink
          v-for="link in filteredLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view></router-view>
    </q-page-container>
  </q-layout>
</template>

<script>
import { defineComponent, onBeforeMount, ref } from "vue";
import EssentialLink from "components/EssentialLink.vue";
import { useAuthStore } from "src/stores/auth";
import { useRouter } from "vue-router";

const linksList = [
  {
    title: "Cliente",
    icon: "person",
    link: "/#/cliente"
  },
  {
    title: "Proposta",
    icon: "description",
    link: "/#/proposta"
  },
  {
    title: "Cômite",
    icon: "fact_check",
    link: "/#/comite"
  },
  {
    title: "Informações",
    icon: "help_outline",
    link: "/"
  }
];

export default defineComponent({
  name: "MainLayout",

  components: {
    EssentialLink
  },

  setup() {
    const leftDrawerOpen = ref(false);
    const authStore = useAuthStore();
    const router = useRouter();
    let filteredLinks = ref([]);
    autLogin();

    function autLogin() {
      if (authStore.getRoles.includes("ROLE_COORDENADOR")) {
        filteredLinks.value = linksList.filter(
          (link) => link.title !== "Cliente" && link.title !== "Proposta"
        );
      } else {
        filteredLinks.value = linksList;
        filteredLinks.value = linksList.filter(
          (link) => link.title !== "Cômite"
        );
      }

    }

    onBeforeMount(autLogin);

    return {
      essentialLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
      logout() {
        authStore.logout();
        router.push({
          name: "login"
        });
      },
      authStore,
      filteredLinks
    };
  }
});
</script>
