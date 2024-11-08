import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import DataCenters from  '@/pages/DataCenters.vue';
import DataCenterDetail from  '@/pages/DataCenterDetail.vue';
import Racks from  '@/pages/Racks.vue';
import RackDetail from  '@/pages/RackDetail.vue';
import Equipments from  '@/pages/Equipments.vue';
import EquipmentDetail from  '@/pages/EquipmentDetail.vue';
import StaticTransferSwitchs from  '@/pages/StaticTransferSwitchs.vue';
import StaticTransferSwitchDetail from  '@/pages/StaticTransferSwitchDetail.vue';
import PowerSupplys from  '@/pages/PowerSupplys.vue';
import PowerSupplyDetail from  '@/pages/PowerSupplyDetail.vue';
import Generators from  '@/pages/Generators.vue';
import GeneratorDetail from  '@/pages/GeneratorDetail.vue';
import TemperatureSensors from  '@/pages/TemperatureSensors.vue';
import TemperatureSensorDetail from  '@/pages/TemperatureSensorDetail.vue';
import CoolingUnits from  '@/pages/CoolingUnits.vue';
import CoolingUnitDetail from  '@/pages/CoolingUnitDetail.vue';
import PowerStrips from  '@/pages/PowerStrips.vue';
import PowerStripDetail from  '@/pages/PowerStripDetail.vue';
import NetworkSwitchs from  '@/pages/NetworkSwitchs.vue';
import NetworkSwitchDetail from  '@/pages/NetworkSwitchDetail.vue';
import MonitoringPoints from  '@/pages/MonitoringPoints.vue';
import MonitoringPointDetail from  '@/pages/MonitoringPointDetail.vue';
import SurveillanceCameras from  '@/pages/SurveillanceCameras.vue';
import SurveillanceCameraDetail from  '@/pages/SurveillanceCameraDetail.vue';
import MaintenanceSchedules from  '@/pages/MaintenanceSchedules.vue';
import MaintenanceScheduleDetail from  '@/pages/MaintenanceScheduleDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import EventLogs from  '@/pages/EventLogs.vue';
import EventLogDetail from  '@/pages/EventLogDetail.vue';
import CapacityReports from  '@/pages/CapacityReports.vue';
import CapacityReportDetail from  '@/pages/CapacityReportDetail.vue';
import EnergyConsumptions from  '@/pages/EnergyConsumptions.vue';
import EnergyConsumptionDetail from  '@/pages/EnergyConsumptionDetail.vue';
import Issues from  '@/pages/Issues.vue';
import IssueDetail from  '@/pages/IssueDetail.vue';
import IssueResolutions from  '@/pages/IssueResolutions.vue';
import IssueResolutionDetail from  '@/pages/IssueResolutionDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/dataCenters',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/dataCenters',
		name: 'DataCenters',
		layout: DefaultLayout,
		component: DataCenters,
	},
	{
	    path: '/dataCenter/:dataCenterId', 
	    name: 'DataCenterDetail',
		layout: DefaultLayout,
	    component: DataCenterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/racks',
		name: 'Racks',
		layout: DefaultLayout,
		component: Racks,
	},
	{
	    path: '/rack/:rackId', 
	    name: 'RackDetail',
		layout: DefaultLayout,
	    component: RackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/equipments',
		name: 'Equipments',
		layout: DefaultLayout,
		component: Equipments,
	},
	{
	    path: '/equipment/:equipmentId', 
	    name: 'EquipmentDetail',
		layout: DefaultLayout,
	    component: EquipmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/staticTransferSwitchs',
		name: 'StaticTransferSwitchs',
		layout: DefaultLayout,
		component: StaticTransferSwitchs,
	},
	{
	    path: '/staticTransferSwitch/:staticTransferSwitchId', 
	    name: 'StaticTransferSwitchDetail',
		layout: DefaultLayout,
	    component: StaticTransferSwitchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerSupplys',
		name: 'PowerSupplys',
		layout: DefaultLayout,
		component: PowerSupplys,
	},
	{
	    path: '/powerSupply/:powerSupplyId', 
	    name: 'PowerSupplyDetail',
		layout: DefaultLayout,
	    component: PowerSupplyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/generators',
		name: 'Generators',
		layout: DefaultLayout,
		component: Generators,
	},
	{
	    path: '/generator/:generatorId', 
	    name: 'GeneratorDetail',
		layout: DefaultLayout,
	    component: GeneratorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/temperatureSensors',
		name: 'TemperatureSensors',
		layout: DefaultLayout,
		component: TemperatureSensors,
	},
	{
	    path: '/temperatureSensor/:temperatureSensorId', 
	    name: 'TemperatureSensorDetail',
		layout: DefaultLayout,
	    component: TemperatureSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coolingUnits',
		name: 'CoolingUnits',
		layout: DefaultLayout,
		component: CoolingUnits,
	},
	{
	    path: '/coolingUnit/:coolingUnitId', 
	    name: 'CoolingUnitDetail',
		layout: DefaultLayout,
	    component: CoolingUnitDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerStrips',
		name: 'PowerStrips',
		layout: DefaultLayout,
		component: PowerStrips,
	},
	{
	    path: '/powerStrip/:powerStripId', 
	    name: 'PowerStripDetail',
		layout: DefaultLayout,
	    component: PowerStripDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/networkSwitchs',
		name: 'NetworkSwitchs',
		layout: DefaultLayout,
		component: NetworkSwitchs,
	},
	{
	    path: '/networkSwitch/:networkSwitchId', 
	    name: 'NetworkSwitchDetail',
		layout: DefaultLayout,
	    component: NetworkSwitchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/monitoringPoints',
		name: 'MonitoringPoints',
		layout: DefaultLayout,
		component: MonitoringPoints,
	},
	{
	    path: '/monitoringPoint/:monitoringPointId', 
	    name: 'MonitoringPointDetail',
		layout: DefaultLayout,
	    component: MonitoringPointDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/surveillanceCameras',
		name: 'SurveillanceCameras',
		layout: DefaultLayout,
		component: SurveillanceCameras,
	},
	{
	    path: '/surveillanceCamera/:surveillanceCameraId', 
	    name: 'SurveillanceCameraDetail',
		layout: DefaultLayout,
	    component: SurveillanceCameraDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/maintenanceSchedules',
		name: 'MaintenanceSchedules',
		layout: DefaultLayout,
		component: MaintenanceSchedules,
	},
	{
	    path: '/maintenanceSchedule/:maintenanceScheduleId', 
	    name: 'MaintenanceScheduleDetail',
		layout: DefaultLayout,
	    component: MaintenanceScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: DefaultLayout,
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: DefaultLayout,
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/eventLogs',
		name: 'EventLogs',
		layout: DefaultLayout,
		component: EventLogs,
	},
	{
	    path: '/eventLog/:eventLogId', 
	    name: 'EventLogDetail',
		layout: DefaultLayout,
	    component: EventLogDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/capacityReports',
		name: 'CapacityReports',
		layout: DefaultLayout,
		component: CapacityReports,
	},
	{
	    path: '/capacityReport/:capacityReportId', 
	    name: 'CapacityReportDetail',
		layout: DefaultLayout,
	    component: CapacityReportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/energyConsumptions',
		name: 'EnergyConsumptions',
		layout: DefaultLayout,
		component: EnergyConsumptions,
	},
	{
	    path: '/energyConsumption/:energyConsumptionId', 
	    name: 'EnergyConsumptionDetail',
		layout: DefaultLayout,
	    component: EnergyConsumptionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/issues',
		name: 'Issues',
		layout: DefaultLayout,
		component: Issues,
	},
	{
	    path: '/issue/:issueId', 
	    name: 'IssueDetail',
		layout: DefaultLayout,
	    component: IssueDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/issueResolutions',
		name: 'IssueResolutions',
		layout: DefaultLayout,
		component: IssueResolutions,
	},
	{
	    path: '/issueResolution/:issueResolutionId', 
	    name: 'IssueResolutionDetail',
		layout: DefaultLayout,
	    component: IssueResolutionDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
