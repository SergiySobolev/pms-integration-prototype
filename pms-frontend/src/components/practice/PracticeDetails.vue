<template>

    <div id="practice-details">

        <PracticeAttributes v-if="practice" :practice="practice"/>

        <Providers v-if="practice" :practice="practice"/>

        <Departments v-if="practice" :practice="practice"/>

        <div class="row">

            <div class="col-md-9"></div>

            <div class="col-md-1">

                <router-link to="/practices">
                    <input type="button" value="Back to Practices" class="btn btn-info"/>
                </router-link>

            </div>

            <div class="col-md-2">
                <BackToHome/>
            </div>

        </div>

    </div>


</template>

<script>
    import BackToHome from "../BackToHome";
    import PracticeAttributes from "./PracticeAttributes";
    import Departments from "./Departments";
    import Providers from "./Providers";
    import {ServicesFactory} from "../../services/ServicesFactory";

    const pmsService = ServicesFactory.get("pms");

    export default {
        name: 'PracticeDetails',
        components: {BackToHome, PracticeAttributes, Departments, Providers},
        props: ['practiceid'],
        data() {
            return {
                practice: null,
            };
        },
        created: function () {
            this.fetch();
        },
        methods: {
            async fetch() {
                pmsService.getPractice(this.practiceid).then(res => {
                    this.practice = res.data;
                });
            }
        }
    }
</script>

