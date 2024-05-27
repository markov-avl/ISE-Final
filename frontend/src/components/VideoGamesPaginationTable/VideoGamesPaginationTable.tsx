import React, { useEffect, useState } from 'react'
import PaginationTable from '@/shared/PaginationTable/PaginationTable'
import { get } from '@/api/baseApi'
import { DataCustomNameByField } from '@/types/DataCustomNameByField'

interface VideoGamesPaginationTableProps {
	setCurrentPage: (p: number) => void
	query: QueryModel
}

const VideoGamesPaginationTable = ({ setCurrentPage, query }: VideoGamesPaginationTableProps) => {
	const [page, setPage] = useState<PageModel>()
	const getVideoGamesPage = () => {
		return get('sales/extended', new URLSearchParams(query).toString())
	}

	const columns: DataCustomNameByField<Game, keyof Game>[] = [
		{
			key: 'id',
			name: 'ID'
		},
		{
			key: 'name',
			name: 'Название'
		},
		{
			key: 'year',
			name: 'Год издания'
		},
		{
			key: 'publisher',
			name: 'Издатель'
		},
		{
			key: 'platform',
			name: 'Платформа'
		},
		{
			key: 'genre',
			name: 'Жанр'
		},
		{
			key: 'numberOfSales',
			name: 'Продажи (млн. шт.)'
		},
		{
			key: 'region',
			name: 'Регион'
		}
	]

	useEffect(() => {
		getVideoGamesPage().then(data => setPage(data))
	}, [query])

	return (
		<div>
			<PaginationTable
				data={page?.data || []}
				columns={columns}
				totalPages={page?.totalPages || 0}
				onNextPage={() => setCurrentPage(1)}
				onPrevPage={() => setCurrentPage(-1)}
				currentPage={page?.page || 0}
			/>
		</div>
	)
}

export default VideoGamesPaginationTable