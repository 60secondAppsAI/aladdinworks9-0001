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
            <issueResolution-table
            v-if="issueResolutions && issueResolutions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:issueResolutions="issueResolutions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-issue-resolutions="getAllIssueResolutions"
             >

            </issueResolution-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import IssueResolutionTable from "@/components/IssueResolutionTable";
import IssueResolutionService from "../services/IssueResolutionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    IssueResolutionTable,
  },
  data() {
    return {
      issueResolutions: [],
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
    async getAllIssueResolutions(sortBy='issueResolutionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await IssueResolutionService.getAllIssueResolutions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.issueResolutions.length) {
					this.issueResolutions = response.data.issueResolutions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching issueResolutions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching issueResolution details:", error);
      }
    },
  },
  mounted() {
    this.getAllIssueResolutions();
  },
  created() {
    this.$root.$on('searchQueryForIssueResolutionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllIssueResolutions();
    })
  }
};
</script>
<style></style>
