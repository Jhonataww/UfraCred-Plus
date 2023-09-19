const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("pages/IndexPage.vue"),
      },
    ],
  },
  {
    name: "login",
    path: "/login",
    component: () => import("pages/Login/LoginPage.vue"),
  },
  {
    path: "/esqueciMinhaSenha",
    component: () => import("pages/Login/EsqueciMinhaSenha.vue"),
  },
  {
    path: "/NovaSenha",
    component: () => import("pages/Login/NovaSenha.vue"),
  },
  {
    path: "/usuario",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("pages/Usuario/UsuarioListagem.vue"),
      },
    ],
  },
  {
    path: "/usuarioPerfil",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("pages/Usuario/UsuarioPerfil.vue"),
      },
    ],
  },
  {
    path: "/usuario/novo",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },

        component: () => import("pages/Usuario/UsuarioForm.vue"),
      },
    ],
  },
  {
    path: "/usuario/:usuarioId",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },

        component: () => import("pages/Usuario/UsuarioForm.vue"),
      },
    ],
  },
  //fluxo
  {
    path: "/proposta",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("pages/Proposta/PropostaListagem.vue"),
      },
    ],
  },
  {
    path: "/proposta/novo",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("src/pages/Proposta/PropostaForm.vue"),
      },
    ],
  },
  {
    path: "/proposta/:propostaId",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("src/pages/Proposta/PropostaForm.vue"),
      },
    ],
  },

  //Cliente
  {
    path: "/cliente",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("pages/Cliente/ClienteListagem.vue"),
      },
    ],
  },
  {
    path: "/cliente/novo",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("src/pages/Cliente/ClienteForm.vue"),
      },
    ],
  },
  {
    path: "/cliente/:clienteId",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("src/pages/Cliente/ClienteForm.vue"),
      },
    ],
  },

  {
    path: "/comite",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("pages/Comite/ComiteListagem.vue"),
      },
    ],
  },
  {
    path: "/comite/novo",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("src/pages/Comite/ComiteForm.vue"),
      },
    ],
  },
  {
    path: "/comite/:comiteId",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        meta: { requireLogin: true },
        component: () => import("src/pages/Comite/ComiteForm.vue"),
      },
    ],
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
