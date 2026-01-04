import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/tableZ01',
      name: 'tableZ01',
      component: () => import('../views/TableZ01.vue')
    },
    {
      path: '/tableA01',
      name: 'tableA01',
      component: () => import('../views/TableA01.vue')
    },
    {
      path: '/tableB01',
      name: 'tableB01',
      component: () => import('../views/TableB01.vue')
    },
    {
      path: '/tableB02',
      name: 'tableB02',
      component: () => import('../views/TableB02.vue')
    },
    {
      path: '/tableC01',
      name: 'tableC01',
      component: () => import('../views/TableC01.vue')
    },
    {
      path: '/tableD01',
      name: 'tableD01',
      component: () => import('../views/TableD01.vue')
    },
    {
      path: '/tableE01',
      name: 'tableE01',
      component: () => import('../views/TableE01.vue')
    },
    {
      path: '/tableF01',
      name: 'tableF01',
      component: () => import('../views/TableF01.vue')
    },
    {
      path: '/tableG01',
      name: 'tableG01',
      component: () => import('../views/TableG01.vue')
    },
    {
      path: '/tableG02',
      name: 'tableG02',
      component: () => import('../views/TableG02.vue')
    },
    {
      path: '/tableG03',
      name: 'tableG03',
      component: () => import('../views/TableG03.vue')
    },
    {
      path: '/tableG04',
      name: 'tableG04',
      component: () => import('../views/TableG04.vue')
    },
    {
      path: '/tableG05',
      name: 'tableG05',
      component: () => import('../views/TableG05.vue')
    },
    {
      path: '/tableG06',
      name: 'tableG06',
      component: () => import('../views/TableG06.vue')
    },
    {
      path: '/tableG07',
      name: 'tableG07',
      component: () => import('../views/TableG07.vue')
    },
    {
      path: '/tableH01',
      name: 'tableH01',
      component: () => import('../views/TableH01.vue')
    },
    {
      path: '/tableH02',
      name: 'tableH02',
      component: () => import('../views/TableH02.vue')
    },
    {
      path: '/tableH03',
      name: 'tableH03',
      component: () => import('../views/TableH03.vue')
    },
    {
      path: '/tableH04',
      name: 'tableH04',
      component: () => import('../views/TableH04.vue')
    },
    {
      path: '/tableH05',
      name: 'tableH05',
      component: () => import('../views/TableH05.vue')
    },
    {
      path: '/tableH06',
      name: 'tableH06',
      component: () => import('../views/TableH06.vue')
    },
    {
      path: '/tableH07',
      name: 'tableH07',
      component: () => import('../views/TableH07.vue')
    },
    {
      path: '/tableH08',
      name: 'tableH08',
      component: () => import('../views/TableH08.vue')
    },
    {
      path: '/tableH09',
      name: 'tableH09',
      component: () => import('../views/TableH09.vue')
    },
    {
      path: '/tableH10',
      name: 'tableH10',
      component: () => import('../views/TableH10.vue')
    },
    {
      path: '/tableH11',
      name: 'tableH11',
      component: () => import('../views/TableH11.vue')
    },
    {
      path: '/tableI01',
      name: 'tableI01',
      component: () => import('../views/TableI01.vue')
    },
    {
      path: '/tableJ01',
      name: 'tableJ01',
      component: () => import('../views/TableJ01.vue')
    },
    {
      path: '/disaster-statistics',
      name: 'disasterStatistics',
      component: () => import('../views/DisasterStatistics.vue')
    },
    {
      path: '/disaster-statistics-import',
      name: 'disasterStatisticsImport',
      component: () => import('../views/DisasterStatisticsImport.vue')
    },
  ]
})

export default router
