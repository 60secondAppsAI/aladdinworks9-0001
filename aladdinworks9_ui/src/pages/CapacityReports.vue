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
            <capacityReport-table
            v-if="capacityReports && capacityReports.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:capacityReports="capacityReports"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-capacity-reports="getAllCapacityReports"
             >

            </capacityReport-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CapacityReportTable from "@/components/CapacityReportTable";
import CapacityReportService from "../services/CapacityReportService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CapacityReportTable,
  },
  data() {
    return {
      capacityReports: [],
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
    async getAllCapacityReports(sortBy='capacityReportId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CapacityReportService.getAllCapacityReports(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.capacityReports.length) {
					this.capacityReports = response.data.capacityReports;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching capacityReports:", error);
        }
        
      } catch (error) {
        console.error("Error fetching capacityReport details:", error);
      }
    },
  },
  mounted() {
    this.getAllCapacityReports();
  },
  created() {
    this.$root.$on('searchQueryForCapacityReportsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCapacityReports();
    })
  }
};
</script>
<style></style>
