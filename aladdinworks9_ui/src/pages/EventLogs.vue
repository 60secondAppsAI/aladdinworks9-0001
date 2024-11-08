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
            <eventLog-table
            v-if="eventLogs && eventLogs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:eventLogs="eventLogs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-event-logs="getAllEventLogs"
             >

            </eventLog-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import EventLogTable from "@/components/EventLogTable";
import EventLogService from "../services/EventLogService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    EventLogTable,
  },
  data() {
    return {
      eventLogs: [],
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
    async getAllEventLogs(sortBy='eventLogId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await EventLogService.getAllEventLogs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.eventLogs.length) {
					this.eventLogs = response.data.eventLogs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching eventLogs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching eventLog details:", error);
      }
    },
  },
  mounted() {
    this.getAllEventLogs();
  },
  created() {
    this.$root.$on('searchQueryForEventLogsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllEventLogs();
    })
  }
};
</script>
<style></style>
