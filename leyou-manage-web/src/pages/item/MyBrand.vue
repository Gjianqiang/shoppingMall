<template>
    <div>
      <v-layout class="px-3 pb-2">
        <v-flex xs2><v-btn color="info" >新增品牌</v-btn></v-flex>
        <v-spacer />
        <v-flex xs4><v-text-field label="搜索" hide-details append-icon="search" v-model="key"></v-text-field></v-flex>
      </v-layout> 
        <v-data-table
      :headers="headers"
      :items="brands"
      :search="search"
      :pagination.sync="pagination"
      :total-items="totalBrands"
      :loading="loading"
      class="elevation-1"
  >
    <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.name }}</td>
        <td class="text-xs-center">
          <img v-if="props.item.image" :src="props.item.image" width="130" height="40">
          <span v-else>无</span>
        </td>
        <td class="text-xs-center">{{ props.item.letter }}</td>>
        <td class="justify-center layout">
        <v-btn color="info" small><v-icon>edit</v-icon></v-btn>
        <v-btn color="error" small><v-icon>delete</v-icon></v-btn>
    </td>
    </template>
  </v-data-table>
    </div>
</template>
<script>
export default {
    name:"MyBrand",
    data () {
        return {
            headers:[{ text: '品牌ID',align: 'center',sortable: true,value: 'id'},
                     { text: '品牌名称',align: 'center',sortable: false,value: 'name'},
                     { text: '品牌LOGO',align: 'center',sortable: false,value: 'image'},
                     { text: '品牌首字母',align: 'center',sortable: true,value: 'letter'},
                     { text: '操作',align: 'center',sortable: false,value: 'operate'},
            ],
           search: '', // 搜索过滤字段
           totalBrands: 0, // 总条数
           brands: [], // 当前页品牌数据
           loading: true, // 是否在加载中
           pagination: {}, // 分页信息 
           key:""//查询字符串,搜索条件
        }
    },
    created(){
      this.loading= false
          
    },
    watch:{
      key(){//监控变量
        console.log(this.key)
        this.pagination.page=1;//pagination改变触发下面的监控
        //this.loadBrands()
      },
      pagination:{//监控对象
        deep:true,
        handler(){
          this.loadBrands();
        }
      }
    },
    methods:{
      loadBrands(){
        this.$http.get("/item/brand/page",{
        params:{
          page:this.pagination.page,//AuthenticatorAssertionResponse
          rows:this.pagination.rowsPerPage,//每页大小
          sortBy:this.pagination.sortBy,//排序字段
          desc:this.pagination.descending,//是否降序
          key:this.key//搜索条件          
        }
      }).then(response =>{
        this.loading=true
        console.log(response.data)
        this.brands=response.data.items
        this.totalBrands=response.data.total
        this.loading=false
      })    
      }
    }
}
</script>
<style scoped>

</style>