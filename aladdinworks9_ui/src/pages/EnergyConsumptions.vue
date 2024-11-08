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
            <energyConsumption-table
            v-if="energyConsumptions && energyConsumptions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:energyConsumptions="energyConsumptions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-energy-consumptions="getAllEnergyConsumptions"
             >

            </energyConsumption-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import EnergyConsumptionTable from "@/components/EnergyConsumptionTable";
import EnergyConsumptionService from "../services/EnergyConsumptionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    EnergyConsumptionTable,
  },
  data() {
    return {
      energyConsumptions: [],
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
    async getAllEnergyConsumptions(sortBy='energyConsumptionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await EnergyConsumptionService.getAllEnergyConsumptions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.energyConsumptions.length) {
					this.energyConsumptions = response.data.energyConsumptions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching energyConsumptions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching energyConsumption details:", error);
      }
    },
  },
  mounted() {
    this.getAllEnergyConsumptions();
  },
  created() {
    this.$root.$on('searchQueryForEnergyConsumptionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllEnergyConsumptions();
    })
  }
};
</script>
<style></style>
