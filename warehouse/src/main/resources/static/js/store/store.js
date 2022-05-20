import Vue from 'vue'
import Vuex from 'vuex'
import messagesApi from 'api/messages'
import commentsApi from 'api/comment'


Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        // messages: frontendData.messages,
        messages: messages
        // profile: frontendData.profile,
        // comments: frontendData.messages.comments

    },
    getters: {

    },
    mutations: {
        addMessageMutation(state, message) {
            state.messages = [
                ...state.messages,
                message
            ]
        },
        updateMessageMutation(state, message) {
            const updateIndex = state.messages.findIndex(item => item.id === message.id)

            state.messages = [
                ...state.messages.slice(0, updateIndex),
                message,
                ...state.messages.slice(updateIndex + 1)
            ]
        },
        removeMessageMutation(state, message) {
            const deletionIndex = state.messages.findIndex(item => item.id === message.id)

            if (deletionIndex > -1) {
                state.messages = [
                    ...state.messages.slice(0, deletionIndex),
                    ...state.messages.slice(deletionIndex + 1)
                ]
            }
        },
        addCommentMutation(state, comment) {

            /*state.messages = [
                ...state.messages,
                message
            ]*/

            const updateIndex = state.messages.findIndex(item => item.id === comment.message.id)
console.log(updateIndex)
            const message = state.messages[updateIndex]
            console.log(message)
            if(!message.comments.find(it => it.id === comment.id)) {
                state.messages = [
                    ...state.messages.slice(0, updateIndex),
                    {
                        ...message,
                        comments: [
                            ...message.comments,
                            comment
                        ]
                    },
                    ...state.messages.slice(updateIndex + 1)
                ]
            }

        },
    },
    actions: {
        async addMessageAction({commit, state}, message) {
            const result = await messagesApi.add(message)
            const data = await result.json()
            const index = state.messages.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateMessageMutation', data)
            } else {
                commit('addMessageMutation', data)
            }
        },
        async updateMessageAction({commit}, message) {
            const result = await messagesApi.update(message)
            const data = await result.json()
            commit('updateMessageMutation', data)
        },
        async removeMessageAction({commit}, message) {
            const result = await messagesApi.remove(message.id)

            if (result.ok) {
                commit('removeMessageMutation', message)
            }
        },
        async addCommentAction({commit, state}, comment){
            const response = await commentsApi.add(comment)
            const data = await response.json()
            // commit('addCommentMutation', comment)
            commit('addCommentMutation', data)

        }
    }
})