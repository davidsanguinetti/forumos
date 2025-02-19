package com.example.forumos.postinsert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Visibility
import com.example.forumos.AppForumos
import com.example.forumos.databinding.FragmentInsertpostBinding
import com.example.forumos.model.ForumosPost

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class InsertPostFragment : Fragment() {

    private var _binding: FragmentInsertpostBinding? = null
    private lateinit var vmodel : InsertPostViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vmodel = ViewModelProvider(this).get(InsertPostViewModel::class.java)

        _binding = FragmentInsertpostBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmodel.successInsertion.observe(viewLifecycleOwner) { success ->
            if (success) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Sucesso")
                    .setMessage("Post introduzido com sucesso!")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss();
                        findNavController().popBackStack()
                    }
                    .show()

            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle("Erro")
                    .setMessage("Ocorreu um erro a introduzir o post")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss();
                    }
                    .show()
            }
        }

        val args :InsertPostFragmentArgs by navArgs<InsertPostFragmentArgs>()
        if (args.idpostResponder == 1) {
            _binding!!.llRespostaa.visibility = View.GONE
        }

        _binding!!.btPublicar.setOnClickListener {
            val post = ForumosPost(
                _binding!!.etTitle.text.toString(),
                -1,
                mensagem = _binding!!.etMessage.text.toString(),
                autor = (requireActivity().application as AppForumos).userName,
                id_parentpost = args.idpostResponder
            )

            vmodel.insertPost(post)
        }
//        binding..setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}