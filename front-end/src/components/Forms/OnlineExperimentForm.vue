<template>
  <va-card>
      <div style="height: 10px" />
      <a-space style="margin-bottom: 20px">
      <div style="font-size: 30px; font-weight: bold">实验报告：{{taskName}}</div>
      <div v-show="isSubmitted"><va-icon color="#89d7bc" name="check_circle" />已提交</div>
      <div v-show="!isSubmitted"><va-icon name="cancel" />未提交</div>
      </a-space>
      <div />
      <a-space style="margin-bottom: 20px">
          <span class="label">获取实验说明文档：</span>
          <va-button color="#e2e0df" style="color: black" @click="getTaskGuide">点击获取</va-button>
          </a-space>
      <!-- <a-form ref="formRef" v-model="form" @submit="onHandleSubmit" style="width: 100%">
          <a-space>
            <a-form-item style="width: 250px" field='id' label='学号'
            :rules="[{required:true,message:'请填入学号'}, {type:'number',max: 7,message: '请填入正确格式的学号'}]">
                <a-input v-model="form.id"/>
            </a-form-item>
            <a-form-item style="width: 250px" field='name' label='姓名'
            :rules="[{required: true, message: '请填入姓名'}]">
                <a-input v-model="form.name"/>
            </a-form-item>
            <a-form-item style="width: 250px" field='cooperator' label='合作者'>
                <a-input v-model="form.cooperator" />
            </a-form-item>
          </a-space>
          <a-space>
              <a-form-item style="width: 300px" field="place" label="实验地点">
                  <a-input v-model="form.place" />
              </a-form-item>
              <a-form-item style="width: 300px" field='date' label='实验时间'>
                  <a-date-picker v-model="form.date" />
              </a-form-item>
          </a-space>
          <a-form-item style="width: 600px" field="aim" label="实验目的">
              <a-input type="textarea" :rows="5" />
          </a-form-item>

      </a-form> -->

      <va-form>
          <a-space>
            <span class="label">学号</span>
            <va-input v-model="form_studentid" />
            <span class="label">姓名</span>
          <va-input v-model="form_name" />
          <span class="label">合作者</span>
          <va-input v-model="form_cooperator" />
          </a-space>
          <div style="height: 20px" />
          <a-space>
              <span class="label">实验地点</span>
              <va-input v-model="form_place" />
              <span class="label">实验时间</span>
              <a-date-picker v-model="form_date" />
          </a-space>
          <div style="height: 20px" />
          <a-space>
              <span class="label">实验目的</span>
              <va-input class="long-text" type="textarea" v-model="form_aim" />
          </a-space>
          <div style="height: 20px" />
          <a-space>
              <span class="label">实验原理</span>
              <va-input class="long-text" type="textarea" v-model="form_principle" />
          </a-space>
          <div style="height: 20px" />
          <a-space>
              <span class="label">实验设备</span>
              <va-input class="long-text" type="textarea" v-model="form_device" />
          </a-space>
          <div style="height: 20px" />
          <a-space>
              <span class="label">实验步骤</span>
              <va-input class="long-text" type="textarea" v-model="form_procedure" />
          </a-space>
          <div style="height: 20px" />
          <a-space>
              <span class="label">实验现象</span>
              <va-input class="long-text" type="textarea" v-model="form_phenomenon" />
          </a-space>
          <div style="height: 20px" />
          <a-space>
              <span class="label">分析讨论</span>
              <va-input class="long-text" type="textarea" v-model="form_conclusion" />
          </a-space>
          <div style="height: 30px" />
          <va-button color="#9fbcc2" gradient @click="submitTask">提交</va-button>
          <div style="height: 30px" />
      </va-form>

      <!-- <div>
          <div>Values:</div>
          <div>ID: {{form_studentid}}</div>
          <div>姓名: {{form_name}}</div>
          <div>合作者: {{form_cooperator}}</div>
          <div>实验时间: {{form_date}}</div>
          <div>实验地点: {{form_place}}</div>
          <div>实验目的: {{form_aim}}</div>
          <div>实验原理: {{form_principle}}</div>
          <div>实验设备: {{form_device}}</div>
          <div>实验步骤: {{form_procedure}}</div>
          <div>实验现象: {{form_phenomenon}}</div>
          <div>分析讨论: {{form_conclusion}}</div>
      </div> -->
  </va-card>
</template>

<script>
export default {
    data () {
        return {
                studentId: '',
                taskId: '',
                taskName: '',
                deadline: '',

                form_studentid: '',
                form_name: '',
                form_cooperator: '',
                form_date: '',
                form_place: '',
                form_aim: '',
                form_principle: '',
                form_device: '',
                form_procedure: '',
                form_phenomenon: '',
                form_conclusion: '',

            // inputColumns: [
            //     {name: '实验目的'},
            //     {name: '实验原理'},
            //     {name: '实验设备'},
            //     {name: '实验步骤'},
            //     {name: '实验现象'},
            //     {name: '分析讨论'}
            // ],

            courseId: '',

            isSubmitted: false,
            showModal: false,
        }
    },
    mounted () {
        // console.log(this.$route.params.taskId, this.$route.params.taskName)
        this.taskId = this.$route.params.taskId
        this.taskName = this.$route.params.taskName
        this.deadline = this.$route.params.deadline

        /**
         * 还差日期比较的功能还没做 => 超过截止日期后无法提交
         */

        this.studentId = localStorage.getItem("userId")

        fetch(this.$URL + "/finishes/isFinished?studentId=" + this.studentId + "&taskId=" + this.taskId, {
            method: "GET"
        }).then(response => {
            console.log(response)
            let result = response.text()
            result.then(res => {
                if (res == "true") {
                    this.isSubmitted = true
                } else {
                    this.isSubmitted = false
                }
            })
        })

        fetch(this.$URL + "/task/get?id=" + this.taskId, {
            method: "GET"
        }).then(response => {
            let result = response.json()
            result.then(res => {
                this.courseId = res.courseId
                console.log("courseId: ", this.courseId)
            })
        })

    },
    methods: {
        getTaskGuide() {
            window.open(this.$URL + "/file/download/taskGuide/" + this.courseId + "/" + this.taskId + ".pdf");
        },
        submitTask () {

            if (this.isSubmitted) {
                this.$notification.warning('您当前已提交，此次提交将会覆盖之前提交的文件')
            }

            let submitForm = {
                courseId: this.courseId,
                taskId: this.taskId,


                // 按理来说下面的studentId应该填入实验报告里写的id,
                // 但是这个ID如果乱写（和学生本身的ID/数据库中的ID）不匹配的话会报错，
                // 所以最好还是直接给他填了

                studentId: this.studentId,
                studentName: this.form_name,
                cooperator: this.form_cooperator,
                date: this.form_date,
                place: this.form_place,
                content: {
                    aim: this.form_aim,
                    principle: this.form_principle,
                    device: this.form_device,
                    procedure: this.form_procedure,
                    phenomenon: this.form_phenomenon,
                    conclusion: this.form_conclusion
                }
            }
            fetch(this.$URL + "/finishes/upload/online", {
                method: "POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(submitForm)
            }).then(response => {
                console.log(response)
                let result = response.json()
                result.then(res => {
                    console.log(res)
                    if (res.status == 200) {
                        this.$notification.success('上传成功')
                    }
                })
            })
        }
    }
}
</script>

<style>

.label {
    font-size: 16px;
    font-weight: bold
}

.long-text {
    width: 800px;
}

</style>