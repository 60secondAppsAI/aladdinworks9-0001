<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <maintenanceSchedule-table
            v-if="maintenanceSchedules && maintenanceSchedules.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:maintenanceSchedules="maintenanceSchedules"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-maintenance-schedules="getAllMaintenanceSchedules"
             >

            </maintenanceSchedule-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MaintenanceScheduleTable from "@/components/MaintenanceScheduleTable";
import MaintenanceScheduleService from "../services/MaintenanceScheduleService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MaintenanceScheduleTable,
  },
  data() {
    return {
      maintenanceSchedules: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllMaintenanceSchedules(sortBy='maintenanceScheduleId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MaintenanceScheduleService.getAllMaintenanceSchedules(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.maintenanceSchedules.length) {
					this.maintenanceSchedules = response.data.maintenanceSchedules;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching maintenanceSchedules:", error);
        }
        
      } catch (error) {
        console.error("Error fetching maintenanceSchedule details:", error);
      }
    },
  },
  mounted() {
    this.getAllMaintenanceSchedules();
  },
  created() {
    this.$root.$on('searchQueryForMaintenanceSchedulesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMaintenanceSchedules();
    })
  }
};
</script>
<style></style>
